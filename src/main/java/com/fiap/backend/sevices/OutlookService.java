package com.fiap.backend.sevices;

import com.fiap.backend.model.EmailSummaryDTO;
import com.fiap.backend.model.ReceivedEmail;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("OUTLOOK")
public class OutlookService implements EmailService {

    private List<ReceivedEmail> emails = List.of(
            new ReceivedEmail(UUID.fromString("2efff97c-f3e7-4974-a059-ea2b7cfa84c5"), "promo@shopping.com", "usuario@Outlook.com", "Promoção imperdível! 50% OFF em toda a loja", "Confira nossa promoção especial", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("94504cf0-4436-4649-a5c6-3b0caf28f176"), "promo@shopping.com", "usuario@Outlook.com", "Últimos dias para aproveitar o frete grátis!", "Não perca essa oportunidade", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("47ea1feb-4840-467e-abdb-eefecf59d044"), "noreply@banco.com", "usuario@Outlook.com", "Seu extrato bancário está disponível", "Acesse o app para mais informações", new Date(), null, null, true),
            new ReceivedEmail(UUID.fromString("c74e8b33-147f-4611-9032-6854e7eb8289"), "noreply@banco.com", "usuario@Outlook.com", "Pagamento da fatura confirmado", "Seu pagamento foi realizado com sucesso", new Date(), null, null, true),
            new ReceivedEmail(UUID.fromString("a50724cf-e162-4604-b3a3-835d07fa5037"), "faculdade@universidade.com", "usuario@Outlook.com", "Matrícula 2024 aberta - Garanta sua vaga!", "Aproveite nossas condições especiais", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("93950599-b4bf-4e78-9f4e-11b07a7b35a5"), "faculdade@universidade.com", "usuario@Outlook.com", "Calendário acadêmico atualizado", "Veja as novas datas importantes", new Date(), null, null, true),
            new ReceivedEmail(UUID.fromString("ef9d795e-ff33-4e47-8be1-d15ea8dfb128"), "agenda@calendario.com", "usuario@Outlook.com", "Evento importante adicionado à sua agenda", "Reunião às 14h com a equipe", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("4adb2f83-025c-47bc-94a5-e2025c2ea302"), "agenda@calendario.com", "usuario@Outlook.com", "Lembrete: Exame médico amanhã", "Não se esqueça de comparecer", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("262ea236-9017-4e99-b3b8-abd893b58bb8"), "spam@unknown.com", "usuario@Outlook.com", "Você foi sorteado! Resgate seu prêmio agora", "Clique aqui para mais detalhes", new Date(), null, null, false),
            new ReceivedEmail(UUID.fromString("2c42c5ab-ce25-4407-a740-d25f97be00da"), "spam@unknown.com", "usuario@Outlook.com", "Atenção! Sua conta está em risco", "Evite o bloqueio, clique para verificar", new Date(), null, null, false));

    public List<EmailSummaryDTO> getEmailSummaries(String emailAddress) {

        return emails.stream()
                .map(email -> new EmailSummaryDTO(email.getId(), email.getFrom(), email.getReceivedDate(), email.getSubject()))
                .collect(Collectors.toList());
    }

    public Optional<ReceivedEmail> getEmailById(UUID id) {
        return emails.stream()
                .filter(email -> email.getId().equals(id))
                .findFirst();
    }
}
