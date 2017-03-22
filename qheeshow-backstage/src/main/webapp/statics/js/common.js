function dateFormatter(date) {
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
        + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
}
function simpleDateFormatter(date) {
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
        + date.getDate();
}
function isEmpty(str) {
    if (str == null || str == '')
        return true;
    return false;
}