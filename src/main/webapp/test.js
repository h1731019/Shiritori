function redirectToOtherJSP() {
    setTimeout(function() {
        window.location.href = 'NextServlet'; // 遷移先のJSPページのURLを指定
    }, 5000); // 5秒後に遷移する
}

// ページ読み込み完了時に関数を実行する
window.onload = function() {
    redirectToOtherJSP(); // ページ読み込み後に5秒後に遷移する関数を呼び出す
};