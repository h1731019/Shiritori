/**
 * 
 */
function toggleBoard() {
    var board = document.getElementById('board-container');
    var form = document.getElementById('post-form');

    if (board.style.display === 'block') {
        // ボックスが表示されている場合は非表示にする
        board.style.display = 'none';
        form.style.display = 'none';
    } else {
        // ボックスが非表示の場合は表示する
        board.style.display = 'block';
        form.style.display = 'block';
    }

    saveBoxState(); // ボックスの状態を保存
}
window.onload = function() {
    // クッキーからボックスの状態を取得
    var boxState = getCookie("boxState");

    if (boxState === "open") {
        // ボックスが開いている場合は表示
        document.getElementById('board-container').style.display = 'block';
    }
};

// クッキーから値を取得する関数
function getCookie(name) {
    var cookieValue = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return cookieValue ? cookieValue[2] : null;
}
function saveBoxState() {
    var board = document.getElementById('board-container');
    var isOpen = (board.style.display === 'block');

    // クッキーに開閉状態を保存
    document.cookie = "boxState=" + (isOpen ? "open" : "closed");
}
function submitForm() {
    document.getElementById('text-area').style.display = 'block';
    document.getElementById('submit-button').style.display = 'block';
}