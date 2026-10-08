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

    public AssistantService(ChatClient.Builder chatClientBuilder, DishRepository dishRepository) {
        this.chatClient = chatClientBuilder.build();
        this.dishRepository = dishRepository;
    }

    public String ask(String question) {
        List<Dish> dishes = dishRepository.findAll();
        String menuPrompt = dishes.stream()
                .map(d -> String.format("- %s: %s (Preço: R$ %.2f, Stock: %d)",
                        d.getName(), d.getDescription(), d.getPrice(), d.getStock()))
                .collect(Collectors.joining("\n"));

        String systemPrompt = """
                Você é um atendente virtual educado de um restaurante de delivery.
                Responda às perguntas dos clientes de forma curta e direta em português.
                Baseie-se APENAS no cardápio abaixo para responder sobre pratos, ingredientes, preços e estoque.
                Se o cliente fizer perguntas fora do tema do restaurante ou cardápio, recuse educadamente e retorne o assunto para o restaurante.
                
                Cardápio Atual:
                """ + menuPrompt;

        return chatClient.prompt()
                .system(systemPrompt)
                .user(question)
                .call()
                .content();
    }
}