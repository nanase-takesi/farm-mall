$("#q2").onclick = function(event){
        var evt = event || window.event;

        $("#zhuce").style.display = "block";
        $("#denglu").style.display = "none";
        $("#chongzhi").style.display = "none";
    }

    $("#q1").onclick = function(event){
        var evt = event || window.event;

        $("#zhuce").style.display = "none";
        $("#denglu").style.display = "block";
        $("#chongzhi").style.display = "none";
    }

    $("#q3").onclick = function(event){
        var evt = event || window.event;

        $("#zhuce").style.display = "none";
        $("#denglu").style.display = "none";
        $("#chongzhi").style.display = "block";
    }
     $("#q4").onclick = function(event){
        var evt = event || window.event;

        $("#zhuce").style.display = "none";
        $("#denglu").style.display = "block";
        $("#chongzhi").style.display = "none";
    }

    function $(str){
        if(str[0]=="#"){
            return document.getElementById(str.substring(1));
        }else if(str[0]=="."){
            return document.getElementsByClassName(str.substring(1));
        }else{
            return document.getElementsByTagName(str);
        }
    }