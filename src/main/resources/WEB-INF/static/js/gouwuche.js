
$(function(){

    $(".btnAdd").click(function(){
        let $text = $(this).prev();
        $text.val(parseInt($text.val())+1);
        calMoney();
    });
    
    $(".btnReduce").click(function(){
        let $text = $(this).next();
        if($text.val()==0){
            $text.val(0);
        }else{
            $text.val(parseInt($text.val())-1);
        }
        
        calMoney();
    });

    $("#allCheckBox").bindCheckBox($(".goodsBox :checkbox"),calMoney);
});

function calMoney(){
    let money = 0;
    let $subCheckBox = $(".goodsBox :checkbox");
    for(let i=0;i<$subCheckBox.length;i++){
        if($subCheckBox[i].checked){
            let $goodsBox = $($subCheckBox[i].parentNode.parentNode);
            money += parseFloat($goodsBox.find(".price").html())*parseFloat($goodsBox.find(".count").val());
        }
    }
    $("#totalMoney").html(money);
}

function deleteStu(obj){
	if(confirm("亲，您真的要删除吗？")==true){
		obj.parentNode.parentNode.remove();
	}
}


$.fn.extend({
    bindCheckBox:function($subCheckbox,$param){
        let isFn = false;
        if($param){
            if(typeof $param == 'function'){
                isFn = true;
            }
        }

        
        this.click(()=>{
            this.checkAll($subCheckbox);
            isFn&&$param();
        });	
        //每个复选框的点击事件
        $subCheckbox.click(()=>{
            this.backControl($subCheckbox);
            isFn&&$param();
        });

        // if(!isFn){
        //     $param.click(()=>{
        //         this.unChecked($subCheckbox);
        //     })
        // }
    },
    checkAll:function($subCheckbox){
        // this :是调用checkAll函数的对象,this是jQuery对象。
        $subCheckbox.attr("checked",this.attr("checked")=="checked"?true:false);
    },
    unChecked:function($subCheckbox){
        //this:父复选框；
        $subCheckbox.each(function(){
            // this ：循环过程中的当前checkbox，是dom对象。
            this.checked = !this.checked;
        });
        this.backControl($subCheckbox);
    },
    backControl:function($subCheckbox){
        let isCheckAll = true;
        $subCheckbox.each(function(){
            if(!this.checked){
                isCheckAll = false;
            }
        });
        this.attr("checked",isCheckAll);
    }
});