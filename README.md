
# 📰 Headlinr

**Headlinr** is a modern, cross-platform news app built with Kotlin Multiplatform Mobile (KMM). It provides users with the latest headlines and breaking stories from around the world in real time. Powered by [NewsAPI](https://newsapi.org/), Headlinr aggregates top news from multiple reliable sources and offers a clean, intuitive reading experience across Android and iOS.

The app allows users to filter news by country and category, view article details, and stay informed even when offline (upcoming). It’s designed with simplicity, speed, and personalization in mind, making it the ideal daily companion for staying in the loop.

---

## ✨ Features
- Display latest news headlines
- View full article details
- Filter by country
- Filter by topic/category
- View the list of news sources
- Offline screen for network loss

---

## 🗺️ Project Roadmap

**Phase 1 – Core Features**
- Show the latest news list.
- Can view more details from headlines.
- Filter by relevant country.
- Filter by topics. (Bottom sheets).
- Show Sources.
- Offline feature.

**Phase 2 – Personalization & Persistence**
- Subscribe to breaking news and topics via notifications.
- Notifications screen.
- Introduce location to show relevant news by default.
- Introduce offline SQLDelight support.

**Phase 3 – Discovery & Automation**
- Roll out search functionality for specific news or topics.
- Introduce voice search as a bonus feature.
- Automate publishing for both Android and iOS.

**Phase 4 – Monetization & Support**
- Run in-app ads to generate revenue.
- Add a donation option for users to support development.

---

## 🛠️ Tech Stack

- **Kotlin Multiplatform Mobile (KMM)**
- **Jetpack Compose** (Android UI)
- **SwiftUI** or UIKit (iOS UI, optionally via shared logic)
- **NewsAPI** – for real-time news feeds
- **Ktor** – for networking
- **SQLDelight** – for offline persistence (Planned Phase 2)
- **Coroutines & Flow** – for async and reactive programming

---

## 🗂️ Project Structure (MVVM – KMM)
```bash
shared/
├── src/
│   ├── commonMain/
│   │   └── com/yourdomain/yourapp/
│   │       ├── data/
│   │       │   ├── model/           # Data models (DTOs, entities)
│   │       │   ├── repository/      # Repository interfaces & implementations
│   │       ├── di/                  # Dependency injection (e.g., Koin modules)
│   │       ├── network/
│   │       │   ├── service/         # Ktor services / API clients
│   │       ├── presentation/
│   │       │   ├── vm/              # Shared ViewModels
│   │       │   ├── state/           # UI state holders (if applicable)
│   │       ├── util/                # Extensions, helpers, constants
│
│   ├── androidMain/                 # Android-specific code
│   ├── iosMain/                     # iOS-specific code

```


## 🚧 Setup & Installation

1. **Clone the repo:**
   ```bash
   git clone https://github.com/RocqJones/headlinr.git
   ```

2. **Open the project in Android Studio (Giraffe+ recommended)**

3. **Get your NewsAPI key** from [https://newsapi.org](https://newsapi.org) and add it to your config.

4. **Build & run** on Android emulator or iOS simulator.

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change or improve.

---

## 📄 License

[MIT](LICENSE)

---

## 🙏 Support the Project

If you like this project, consider starring the repo or donating in-app once available ❤️

---
