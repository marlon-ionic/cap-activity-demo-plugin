import Foundation

@objc public class ActivityDemo: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
