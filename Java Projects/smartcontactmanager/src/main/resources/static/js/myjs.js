
const toggleSidebar=()=>{

    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
    }

    else{
		
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
    }
}

const search = ()=>{

    // console.log("searching....");
    let query = $("#search-Input").val();

    if(query==''){
        $(".search-container").hide();
        

    }
    else{
        
        
        let url = `http://localhost:8080/search/${query}`;
        fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			
			let text=  `<div class='list-group'`

            // data.forEach(contact => {
            //     text+= `<a href='/user/${contact.cId}/contact'  class='list-group-item list-group-item-action'>${contact.name}</a>`
            // });
            
            for (let i = 0; i < data.length; i++) {
				
                const element = data[i];
                
                text+=`<a></a>`;
                text+=`<a href='/user/${data[i].cId}/contact' class='list-group-item list-group-item-action'>${element.name}</a>`
                
            }


            text+= `</div>`

            $(".search-container").html(text);
			$(".search-container").show();
		})
        
        
    }
}