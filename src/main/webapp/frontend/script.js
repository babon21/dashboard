function greet(element) {
    console.log("Hi, " + name);
    var cw = 0;
    var ch = 0;
    //if (typeof(window.innerWidth) == 'number') {
        // Modern browsers
        cw = window.innerWidth;
        ch = window.innerHeight;
    //} else {
        // IE 8
        //cw = document.documentElement.clientWidth;
      //  ch = document.documentElement.clientHeight;
    //}
    element.$server.getSizeWindow(cw, ch);
}