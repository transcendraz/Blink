<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<title>Ready?</title>

<style>
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  font-family: "Open Sans";
  background-color: #e2e5e6;
  display: flex;
  align-items: center;
  justify-content: center;
}

*,
*:before,
*:after,
button {
  margin: 0;
  padding: 0;
  box-sizing: content-box;
}
*:focus,
*:before:focus,
*:after:focus,
button:focus {
  outline: 0;
}

.icon {
  position: absolute;
  width: 64px;
  height: 64px;
  display: block;
  fill: white;
  top: -20px;
  opacity: 0;
  left: calc(50% - 32px);
}
.icon.active {
  top: 0px;
  opacity: 1;
  transition: 0.3s ease-in-out;
  transition-delay: 4.8s;
}

.btn {
  width: 150px;
  height: 60px;
  position: relative;
  background-color: transparent;
  border: 2px solid #819aa9;
  border-radius: 50px;
  font-family: "Open Sans";
  color: #819aa9;
  font-weight: 400;
  font-size: 1.2em;
  cursor: pointer;
  overflow: hidden;
}
.btn.active { 
  -webkit-animation: upload 7s ease-in-out forwards;
          animation: upload 7s ease-in-out forwards;
} 

.rest { 
  -webkit-transform: scale(0);
          transform: scale(0);
  clip: rect(0, 84px, 84px, 42px);
  height: 84px;
  width: 84px;
  position: absolute;
  left: calc(50% - 42px);
  top: calc(50% - 42px);
}
.rest.active {
  -webkit-animation: animate 1.05s linear 5;
          animation: animate 1.05s linear 5;
  -webkit-animation-delay: 0.6s;
          animation-delay: 0.6s; 
}
.rest.active:after {
  -webkit-animation: animate2 1.05s linear 5;
          animation: animate2 1.05s linear 5;
  -webkit-animation-delay: 0.6s;
          animation-delay: 0.6s;
}
.rest:after {
  -webkit-transform: scale(0);
          transform: scale(0);
  clip: rect(0, 84px, 84px, 42px);
  content: "";
  border-radius: 50%;
  height: 84px;
  width: 84px;
  position: absolute;
}

@-webkit-keyframes upload {
  0% {
    width: 150px;
    height: 60px;
    color: #819aa9;
    background-color: transparent;
  }
  7% {
    width: 155px;
    height: 55px;
    font-size: 1.25em;
    color: #ffffff;
    background-color: #819aa9;
  }
  10% {
    width: 150px;
    height: 60px;
    font-size: 1.2em;
    color: transparent;
  }
  20% {
    width: 60px;
    height: 60px;
    font-size: 0em;
    background-color: transparent;
  }
  90% {
    width: 60px;
    height: 60px;
    color: transparent;
    font-size: 0em;
    background-color: transparent;
  }
  100% {
    width: 150px;
    height: 60px;
    color: transparent;
    font-size: 0em;
    background-color: #819aa9;
  }
}

@keyframes upload {
  0% {
    width: 150px;
    height: 60px;
    color: #819aa9;
    background-color: transparent;
  }
  7% {
    width: 155px;
    height: 55px;
    font-size: 1.25em;
    color: #ffffff;
    background-color: #819aa9;
  }
  10% {
    width: 150px;
    height: 60px;
    font-size: 1.2em;
    color: transparent;
  }
  20% {
    width: 60px;
    height: 60px;
    font-size: 0em;
    background-color: transparent;
  }
  90% {
    width: 60px;
    height: 60px;
    color: transparent;
    font-size: 0em;
    background-color: transparent;
  }
  100% {
    width: 150px;
    height: 60px;
    color: transparent;
    font-size: 0em;
    background-color: #819aa9;
  }
}
@-webkit-keyframes animate {
  0% {
    -webkit-transform: scale(1);
            transform: scale(1);
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  100% {
    -webkit-transform: scale(1);
            transform: scale(1);
    -webkit-transform: rotate(220deg);
            transform: rotate(220deg);
  }
}
@keyframes animate {
  0% {
    -webkit-transform: scale(1);
            transform: scale(1);
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  100% {
    -webkit-transform: scale(1);
            transform: scale(1);
    -webkit-transform: rotate(220deg);
            transform: rotate(220deg);
  }
}
@-webkit-keyframes animate2 {
  0% {
    box-shadow: inset #f36d66 0 0 0 4px;
    -webkit-transform: rotate(-140deg);
            transform: rotate(-140deg);
  }
  100% {
    box-shadow: inset #f36d66 0 0 0 4px;
    -webkit-transform: rotate(140deg);
            transform: rotate(140deg);
  }
}
@keyframes animate2 {
  0% {
    box-shadow: inset #f36d66 0 0 0 4px;
    -webkit-transform: rotate(-140deg);
            transform: rotate(-140deg);
  }
  100% {
    box-shadow: inset #f36d66 0 0 0 4px;
    -webkit-transform: rotate(140deg);
            transform: rotate(140deg);
  }
}
@-webkit-keyframes done {
  0% {
    top: -20px;
  }
  100% {
    top: 0px;
  }
}
@keyframes done {
  0% {
    top: -20px;
  }
  100% {
    top: 0px;
  }
}

  </style>

</head>

<body>
 <script>
 function test(){
    $("button").addClass("active");
    $(".rest").addClass("active");
    $(".icon").addClass("active");
    var sec = 6;
    var timer = setInterval(function(){
        sec--;
        if (sec < 0) {
            clearInterval(timer);
            window.location="chatroom.html";
        }
    }, 1000);
 }
</script>
<svg id="svg-source" height="0" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="position: absolute">
    <g id="check" data-iconmelon="Simple Line Icons:62e3519b8d1506453144dd389ae63264">
        <g>
            <path  d="M22.491,11.622l-7.915,7.632l-4.953-5.138c-0.221-0.229-0.586-0.235-0.812-0.014
                    c-0.228,0.219-0.236,0.581-0.016,0.812l5.354,5.55c0.109,0.112,0.251,0.221,0.414,0.176c0.147,0,0.291-0.06,0.398-0.162
                    l8.326-8.033c0.229-0.219,0.235-0.582,0.016-0.81C23.082,11.409,22.72,11.403,22.491,11.622z"></path>
        </g>
    </g>
</svg>
        
<button class="btn" onclick="test();"> Ready?
    <div class="icon">
        <svg viewBox="0 0 32 32">
            <g filter="">
                <use xlink:href="#check"></use>
            </g>
        </svg>
    </div>
</button>
                
<div class="rest"></div>
</body>
</html>