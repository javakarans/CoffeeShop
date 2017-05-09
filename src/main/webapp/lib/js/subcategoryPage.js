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
$(function () {
        var down=false;
        var scrollLeft=0;
        var x = 0;

        $('.modal-scrollable').mousedown(function(e) {
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
function showModal(int) {
    $('.modalDialog').css('opacity',1);
    $('.img'.concat(int)).css("z-index",1);

};
function closeModal() {
    $('.img-panel').css('z-index',0);
    $('.modalDialog').css('opacity',0);

};


