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
$(function () {
    $('body').click(function (event) {
        if($(event.target).is('#foodListModal')){
            closeFoodListModal();
        }
    })
});
function openFoodListModal(imgItemIndex) {
    $('#foodListModal').css('opacity',1)
        .css('pointer-events','auto');
    $('.img-panel').css('z-index',0);
    $('#img'.concat(imgItemIndex)).css('z-index',1);
};
function closeFoodListModal() {
    $('#foodListModal').css('opacity',0)
        .css('pointer-events','none');
};
