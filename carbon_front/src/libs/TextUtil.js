export function isEmpty(value) {
    return !value || value === '' || value === '--' || value === '/' || value === ' '
}

export function handleText(value, defaultValue) {
    return isEmpty(value) ? defaultValue :value
}