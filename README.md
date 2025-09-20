
# 📰 Headlinr

**Headlinr** is a modern, cross-platform news app built with Kotlin Multiplatform Mobile (KMM). It provides users with the latest headlines and breaking stories from around the world in real time. Powered by [NewsAPI](https://newsapi.org/), Headlinr aggregates top news from multiple reliable sources and offers a clean, intuitive reading experience across Android and iOS.

The app enables users to filter news by country and category, view article details, and stay informed even when offline (coming soon). It’s designed with simplicity, speed, and personalization in mind, making it the ideal daily companion for staying up-to-date.

---

## ✨ Features
- Display breaking news
- View full article details
- Filter by topics/categories
- Search by query
- Offline capability
- Localisation (default based on location/country unless manually set)

---

## 🗺️ Project Roadmap

**Phase 1 – Core Features**
- [x] Show the latest news list: Headlines, Trending, & highlights
- [x] Can view more details based on the selection item.
- [x] Filter by topics.
- [ ] Search relevant news.
- [ ] Deploy Android app to the Play Store

**Phase 2 – Monetization & Support**
- [ ] Run in-app ads to generate revenue.
- [ ] Add a donation option for users to support development.

**Phase 3 – Personalization & Persistence**
- [ ] Subscribe to breaking news and topics via notifications.
- [ ] Notifications setup.
- [ ] Introduce GPS location to show relevant news based on location.
- [ ] Introduce offline support: SQLDelight.

**Phase 4 – Discovery & Automation**
- [ ] Introduce reading assistant.
- [ ] Automate publishing for both Android and iOS.

---

## 🛠️ Technology

- **Kotlin Multiplatform Mobile (KMP)**
- **Jetpack Compose** (Android UI)
- **SwiftUI** (iOS UI)
- **Koin** - Dependency injection
- **Ktor** – for networking
- **Coroutines & Flow** – for async and reactive programming
- **SQLDelight** – for offline persistence
- **MVVM** architecture
- **NewsAPI** – for real-time news source

---

## 🗂️ Project Structure (KMP)
```bash
shared/
├── src/
│   ├── commonMain/
│   │   └── com/domain/ourapp/
│   │       ├── data/
│   │       │   ├── model/           # Data models (DTOs, entities)
│   │       │   ├── repository/      # Repository interfaces & implementations
│   │       ├── di/                  # Dependency injection (Koin modules)
│   │       ├── network/
│   │       │   ├── service/         # Ktor services / API clients
│   │       ├── presentation/
│   │       │   ├── vm/              # Shared ViewModels
│   │       │   ├── state/           # UI state holders
│   │       ├── util/                # Extensions, helpers, constants
│
│   ├── androidMain/
│   │   └── com/domain/ourapp/
│   │       ├── config/            # Android-specific configuration
│   │       ├── utils/             # Android-only utilities
│   │       ├── di/                # Android-specific DI modules
│   │       ├── ui/                # Android UI layer
│   │           ├── common/        # Reusable Android UI components
│   │           ├── navigation/    # Navigation (Jetpack Compose/NavHost)
│   │           ├── screens/       # Android feature screens
│   │           ├── theme/         # Android theming (Compose theme setup)
│
│   ├── iosMain/
│   │   └── com/domain/ourapp/
│   │       ├── Assets/            # iOS resources (images, colors, strings)
│   │       ├── ui/                # iOS UI layer
│   │           ├── screens/       # iOS feature screens
│   │           ├── theme/         # iOS theming (colors, typography, spacing)
│   │           ├── common/        # Reusable UI & wrappers
│   │               ├── contents/  # Reusable UI components (buttons, loaders)
│   │               ├── wrapper/   # Wraps shared ViewModels/state into SwiftUI

```


## 🚧 Setup & Installation

1. **Clone the repo:**
   ```bash
   git clone https://github.com/RocqJones/headlinr.git
   ```

2. **Open the project in Android Studio (Giraffe+ recommended)**

3. **Get your NewsAPI key** from [https://newsapi.org](https://newsapi.org) and add it to your config.

4. **Build & run** on Android emulator or iOS simulator.
5. Other links: [Logo- Canva](https://www.canva.com/design/DAGmlLuVt7k/_2bRxoKA2qySs1OJrP9IcQ/) & [design inspiration](https://app.visily.ai/projects/3f1101b3-b360-464c-8e80-bc8d50565325/boards/1892526)

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
