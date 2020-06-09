/* Automatic Slideshow - change image every 3 seconds */
var myIndex = 0;

function startPlayingCarousel() {
    var htmlCollection = document.getElementsByClassName("my-slides");
    if (htmlCollection == null) {
        return
    }
    for (var i = 0; i < htmlCollection.length; i++) {
        htmlCollection[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > htmlCollection.length) {myIndex = 1}
    htmlCollection[myIndex-1].style.display = "block";
    setTimeout(startPlayingCarousel, 3000);
}