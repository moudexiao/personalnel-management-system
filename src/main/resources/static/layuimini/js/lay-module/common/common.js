layui.define(['layer', 'jquery'], function (exports) {
  var $ = layui.jquery

  var common = {
    //接口地址
    api: 'http://localhost:8080',


    login: function (res) {
      if (res.code == 5000) {
        layer.msg('请登录', { icon: 2, time: 1000 }, function () {
          window.location.href = '/login.html'
        })
      } else if (res.code == 5001) {
        layer.msg('用户没有权限操作', { icon: 2, time: 1000 })
      } else {
        layer.msg('请登录', { icon: 2, time: 1000 }, function () {
          window.location.href = '/login.html'
        })
      }
    },

    getCookie:function(name){
      var prefix = name + "=";
      var start = document.cookie.indexOf(prefix);

      if(start == -1){
        return null;
      }

      var end = document.cookie.indexOf(";",start + prefix.length, end);
      if (end == -1){
        end = document.cookie.length;
      }

      var value = document.cookie.substring(start + prefix.length, end);
      return unescape(value);
    },
    compare: function (data) {
      var compare = function (obj1, obj2) {
        var val1 = obj1.id
        var val2 = obj2.id
        if (val1 < val2) {
          return -1
        } else if (val1 > val2) {
          return 1
        } else {
          return 0
        }
      }
      return data.sort(compare)
    },
  }

  exports('common', common)
})
