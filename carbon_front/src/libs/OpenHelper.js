export function openUrlInNewWindow(url) {
    if (!url || url === '') {
        return
    }
    window.open(url,"_blank")
}