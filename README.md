# Smart Travel Planner Platform

## Proje Açıklaması
Bu proje, **SENG 324 - Software Design Patterns** dersi için geliştirilmiş bir seyahat planlama uygulamasıdır. Farklı şehirler arasında aktivite planlama ve hava durumu takibi yapmanıza olanak sağlar.

## 🎯 Projenin Amacı
Yazılım tasarım desenlerinin gerçek dünyada nasıl uygulanacağını göstermek.

## 📋 Uygulanan Design Patterns

1. **Singleton** - `CityRepository` ile tekil şehir deposu
2. **Strategy** - Ad, Nüfus ve Alan'a göre sıralama
3. **Iterator** - Hava durumuna göre şehir filtreleme
4. **Observer** - Real-time hava durumu güncellemeleri (3 saniye aralığı)
5. **Decorator** - Aktivite ekleme (Müze, Alışveriş, Park, Şehir Merkezi)
6. **Composite** - Hiyerarşik seyahat planı oluşturma
7. **Command** - Geri al (Undo) / İleri al (Redo) işlevleri

## 🖥️ GUI Bileşenleri
- ✅ Şehir listesi ve sıralama kontrolleri
- ✅ Hava durumuna göre filtreleme
- ✅ Aktivite planlayıcı
- ✅ Seyahat planı görüntüsü
- ✅ Sıcaklık bar chart'ı
- ✅ Hava durumu pie chart'ı
- ✅ Undo/Redo butonları

## 📦 Teknolojiler
- **Java 17**
- **JavaFX** - GUI framework
- **Maven** - Build tool
- **Design Patterns** - Yazılım mimarisi

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- Java 17+
- Maven 3.8+

### Çalıştırma
```bash
cd smarttravelplanner
mvn clean javafx:run
```

### JAR Oluşturma
```bash
mvn clean package
```

## 📂 Proje Yapısı
```
src/main/java/com/travelplanner/
├── model/          (City, WeatherState)
├── repository/     (CityRepository - Singleton)
├── strategy/       (Sorting strategies)
├── iterator/       (Weather filtering iterators)
├── observer/       (Weather provider & observer)
├── decorator/      (Activity decorators)
├── composite/      (Activity plan hierarchy)
├── command/        (Command pattern implementation)
└── ui/             (TravelPlannerUI - Main GUI)
```

## 👥 Özellikler
- 🔄 Gerçek zamanlı hava durumu güncellemeleri
- 💾 Geri Al/İleri Al işlevselliği
- 📊 Dinamik grafikler
- 🌍 Çok şehirli seyahat planlama
- 🎯 Aktivite hiyerarşisi yönetimi

## 📝 Not
Proje akademik amaçlar için geliştirilmiştir ve 7 temel yazılım tasarım deseninin uygulanmasını göstermektedir.

---
**Geliştirici**: Design Patterns Team  
**Dersi**: SENG 324 - Software Design Patterns  
**Üniversite**: Ankara Science University  
**Yıl**: 2025-2026 Spring
