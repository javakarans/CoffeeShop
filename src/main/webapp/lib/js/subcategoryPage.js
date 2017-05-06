$(document).ready(function () {
    $(function(){
        var down=false;
        var scrollLeft=0;
        var x = 0;

        $('.scrollable').mousedown(function(e) {
            down = true;
            scrollLeft = this.scrollLeft;
            x = e.clientX;
        }).mouseup(function() {
            down = false;
        }).mousemove(function(e) {
            if (down) {
                this.scrollLeft = scrollLeft + x - e.clientX;
            }
        }).mouseleave(function() {
            down = false;
        });
    });
});
function imgPanel(id) {
    $('#id').css("z-index",3);
};



