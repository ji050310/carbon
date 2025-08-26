const {BrowserWindow, session} = require('electron');
// const https = require('https');
// const jsDom = require("jsdom");
// const icon = require('../common/app-icon');
const icon = {
    iconFile:null
}
const DataStore = require('./app-store');
const dataStore = new DataStore();

//登录某网站获取Cookie通用方法
function getSiteCookie(url, callback) {
    let win = new BrowserWindow(
        {width: 700, height: 600, icon: icon.iconFile, title: '【登陆成功后关闭窗口即可完成设置】'});
    win.loadURL(url).then();
    win.on('close', () => {
        // 查询所有与设置的 URL 相关的所有 cookies.
        session.defaultSession.cookies.get({url: url})
            .then((cookies) => {
                let cookieString = '';
                for (let cookie of cookies) {
                    if (cookie.name === 'XSRF-TOKEN') {
                        dataStore.setShanghaiCookies(cookie.value)
                    }
                    cookieString += cookie.name + '=' + cookie.value + '; '
                }
                console.log("cookie: " + cookieString.trim())
                callback(cookieString.trim())
            })
            .catch((error) => {
                console.log(error)
            })
        win = null
    });
    win.on('page-title-updated', (e) => {
        //阻止窗口标题更改
        e.preventDefault()
    })
}
//登录上海能源交易所
const loginShanghaiTrade = function (item, focusedWindow, event) {
    getSiteCookie('https://www.cneeex.com/', (cookie) => {
        dataStore.setShanghaiCookies(cookie)
    })
};
exports.loginShanghaiTrade = loginShanghaiTrade;
// 登录CSDN
const loginCSDN = function (item, focusedWindow, event) {
    getSiteCookie('https://blog.csdn.net/', (cookie) => {
        dataStore.setCSDNCookie(cookie)
    })
};
exports.loginCSDN = loginCSDN;