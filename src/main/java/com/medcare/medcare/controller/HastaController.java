package com.medcare.medcare.controller;

import com.medcare.medcare.model.Hasta;
import com.medcare.medcare.repository.HastaRepository;
import com.medcare.medcare.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hastalar")
public class HastaController {

    @Autowired
    private HastaRepository hastaRepository;

    // Tüm hastaları getir
    @GetMapping
    public List<Hasta> getAll() {
        return hastaRepository.findAll();
    }

    // Yeni hasta ekle (Multithread örneği içerir)
    @PostMapping
    public Response<Hasta> createHasta(@RequestBody Hasta hasta) {
        try {
            Thread.sleep(1000); // işlem süresi simülasyonu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Hasta saved = hastaRepository.save(hasta);
        return new Response<>(saved, "Hasta başarıyla kaydedildi.");
    }

    // Adın baş harfine göre filtrele (Stream kullanımı)
    @GetMapping("/filtrele")
    public List<Hasta> filtreleAdaGore(@RequestParam String harf) {
        return hastaRepository.findAll().stream()
                .filter(h -> h.getAd().toLowerCase().startsWith(harf.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Ada göre sıralı liste (Stream + Sorted)
    @GetMapping("/sirala")
    public List<Hasta> siralaAdaGore() {
        return hastaRepository.findAll().stream()
                .sorted((h1, h2) -> h1.getAd().compareToIgnoreCase(h2.getAd()))
                .collect(Collectors.toList());
    }

    // TC Kimlik No ile hasta arama
    @GetMapping("/ara")
    public Hasta tcIleAra(@RequestParam String tc) {
        return hastaRepository.findByTcKimlikNo(tc);
    }

    // ID ile hasta getir
    @GetMapping("/{id}")
    public Optional<Hasta> getHastaById(@PathVariable Long id) {
        return hastaRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public Response<String> deleteHasta(@PathVariable Long id) {
        Optional<Hasta> hasta = hastaRepository.findById(id);
        if (hasta.isPresent()) {
            hastaRepository.deleteById(id);
            return new Response<>(null, "Hasta başarıyla silindi.");
        } else {
            return new Response<>(null, "Hasta bulunamadı.");
        }
    }
}
