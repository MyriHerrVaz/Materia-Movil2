import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

/*struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}*/

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("SpaceX Launches")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadLaunches(forceReload: true)
            })
        }
    }

    private func listView() -> AnyView {
        switch viewModel.launches {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let launches):
            return AnyView(List(launches) { launch in
                RocketLaunchRow(rocketLaunch: launch)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}
extension ContentView {
    enum LoadableLaunches {
        case loading
        case result([RocketLaunch])
        case error(String)
    }

    @MainActor
    class ViewModel: ObservableObject {
        @Published var launches = LoadableLaunches.loading

        let helper: KoinHelper = KoinHelper()

        init() {
           self.loadLaunches(forceReload: false)
        }

        func loadLaunches(forceReload: Bool) {
            // TODO: retrieve data
        }
    }
}
func loadLaunches(forceReload: Bool) {
   Task {
       do {
           self.launches = .loading
           let launches = try await helper.getLaunches(forceReload: forceReload)
           self.launches = .result(launches)
       } catch {
           self.launches = .error(error.localizedDescription)
       }
   }
}

extension RocketLaunch: Identifiable { }


