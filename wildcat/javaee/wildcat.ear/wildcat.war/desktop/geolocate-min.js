var Map={_geocoder:null,_infoWindow:null,_map:null,lat:null,lng:null,description:null,address:"",city:"",state:"",postcode:"",country:"",onClick:function(a){Map.geocode({latLng:a.latLng})},openInfoWindow:function(c,b){Map.lat=c.lat().toFixed(5);Map.lng=c.lng().toFixed(5);Map.description=b;var a="("+Map.lat+", "+Map.lng+")<br/>";if(b){a+=b+"<br/>"}a+='<br/><input type="button" value="Pick" onclick="Map.pick();"/>';Map._infoWindow.setContent(a);Map._infoWindow.setPosition(c);Map._infoWindow.open(Map._map)},geocode:function(a){Map._geocoder.geocode(a,function(e,b){Map.description=null;Map.address="";Map.city="";Map.state="";Map.postcode="";Map.country="";if(b==google.maps.GeocoderStatus.OK){var g=e[0].geometry.location;if(a.latLng){g=a.latLng}Map._map.fitBounds(e[0].geometry.viewport);for(var d=0;d<e[0].address_components.length;d++){var h=e[0].address_components[d];for(var c=0;c<h.types.length;c++){var f=h.types[c];if((f=="premise")||(f=="street_address")||(f=="street_number")){Map.address=h.long_name}if(f=="route"){Map.address+=" "+h.long_name}if(f=="locality"){Map.city=h.long_name}if(f=="establishment"){Map.address=h.long_name}if((f=="administrative_area_level_1")||(f=="administrative_area_level_2")||(f=="administrative_area_level_3")){Map.state=h.short_name}if(f=="country"){Map.country=h.long_name}if(f=="postal_code"){Map.postcode=h.long_name}}}Map.openInfoWindow(g,e[0].formatted_address)}else{if(b==google.maps.GeocoderStatus.ZERO_RESULTS){alert("No results found.")}else{alert("Geocode was not successful for the following reason: "+b)}}})},init:function(a){Map._map=new google.maps.Map(document.getElementById("map"),a);Map._infoWindow=new google.maps.InfoWindow();Map._geocoder=new google.maps.Geocoder();Map._searchForm=document.createElement("DIV");Map._searchForm.innerHTML='<b>Click on the map <br/> or type in a search below <br/> to find a location.</b><p><form onsubmit="Map.geocode({address: document.getElementById(\'address\').value});return false;"><input id="address" type="text"><input type="button" value="Search" onclick="Map.geocode({address: document.getElementById(\'address\').value});"/></form>';Map._searchForm.style.fontFamily="Verdana,Geneva,sans-serif";Map._searchForm.style.fontSize="12px";Map._searchForm.style.backgroundColor="white";Map._searchForm.style.borderStyle="solid";Map._searchForm.style.borderWidth="2px";Map._searchForm.style.padding="2px";Map._searchForm.style.margin="5px";Map._searchForm.style.textAlign="center";Map._searchForm.index=1;Map._map.controls[google.maps.ControlPosition.TOP].push(Map._searchForm);google.maps.event.addListener(Map._map,"click",Map.onClick)}};