// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapActivityDemoPlugin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapActivityDemoPlugin",
            targets: ["ActivityDemoPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "ActivityDemoPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ActivityDemoPlugin"),
        .testTarget(
            name: "ActivityDemoPluginTests",
            dependencies: ["ActivityDemoPlugin"],
            path: "ios/Tests/ActivityDemoPluginTests")
    ]
)