window.onload = function(){

	var head = document.getElementsByClassName('head-ul');

	var content = document.getElementsByClassName('content-ul');


	function clearActive(ele){

		var headEle = document.getElementsByClassName(ele);

		for(var i=0; i<headEle[0].children.length; i++){

			headEle[0].children[i].className = '';

		}

	}

	function hide(ele){

		var headEle = document.getElementsByClassName(ele);

		for(var i=0; i<headEle[0].children.length; i++){

			headEle[0].children[i].style.display = 'none';

		}

	}



	function init(){

		hide('content-ul');

		for(var i=0; i<content[0].children.length; i++){

			if(content[0].children[i].className == 'type0'){

				content[0].children[i].style.display = 'block'

			}

		}

	}

	init();

	for(var i=0; i<head[0].children.length; i++){

		(function(i){

			head[0].children[i].onclick = function(){

				clearActive('head-ul');

				head[0].children[i].className='active';

				for(var j=0; j<content[0].children.length; j++){

					content[0].children[j].style.display = 'none';

					if(i == 3){

						content[0].children[j].style.display = 'block'

					}else{

						if(content[0].children[j].className == 'type'+i+''){

							content[0].children[j].style.display = 'block'

						}

					}

				}
			}

		})(i)

	}

}