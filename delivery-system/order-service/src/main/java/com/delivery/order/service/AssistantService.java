package com.delivery.order.service;

import com.delivery.order.entity.Dish;
import com.delivery.order.repository.DishRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssistantService {

    private final ChatClient chatClient;
    private final DishRepository dishRepository;

    public AssistantService(ChatClient.Builder builder, DishRepository dishRepository) {
        this.chatClient = builder.build();
        this.dishRepository = dishRepository;
    }

    public String ask(String question) {
        List<Dish> dishes = dishRepository.findAll();

        String menuContext = dishes.stream()
                .map(d -> d.getName() + " - Preço: R$ " + d.getPrice() + " - Estoque: " + d.getStock())
                .collect(Collectors.joining("\n"));

        String systemPrompt = """
            Você é um atendente do restaurante. Responda em português e com respostas curtas.
            Utilize apenas as informações do cardápio abaixo para responder dúvidas sobre pratos, preços e estoque:
            """ + menuContext + """
            
            Se a pergunta não for sobre o cardápio ou o restaurante, recuse educadamente e peça para focar no cardápio.
            """;

        return chatClient.prompt()
                .system(systemPrompt)
                .user(question)
                .call()
                .content();
    }
}