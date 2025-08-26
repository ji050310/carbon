// utils.js
// 全局函数
export function validateMobile(str) {
    // 检查手机号码格式
    return /^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\d{8}$/.test(
      str,
    );
  }
  
  export function validateEmail(str) {
    // 检查邮箱格式
    return /^([A-Za-z0-9_\-.])+@([A-Za-z0-9_\-.])+\.([A-Za-z]{2,4})$/.test(str);
  }
  
  export function validatePhone(str) {
    // 检查电话格式
    return /^(0\d{2,4}-)?\d{8}$/.test(str);
  }
  
  export function validateQQ(str) {
    // 检查QQ格式
    return /^[1-9][0-9]{4,}$/.test(str);
  }
  
  // 检查验证码格式
  export function validateSmsCode(str) {
    return /^\d4$/.test(str);
  }

  // 检验字符空
  export function validateIsEmpty(str) {
    return  str === '' || !str;
  }
  
  