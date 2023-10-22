export function safariWorkaround(node) {
    if (window.navigator.userAgent.includes("Safari")) {
        node.style.overflow = "hidden";
    }
}