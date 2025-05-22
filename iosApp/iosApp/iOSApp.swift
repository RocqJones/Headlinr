import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        // locate and init koin here
        KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			SplashScreen(viewModelWrapper: InterfaceViewModelWrapper())
		}
	}
}
