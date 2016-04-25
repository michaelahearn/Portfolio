//Michael Ahearn - W0202544 - WEBD2000 - Final Project - JPOS
var jsonLoader = new (function()
{
	this.http = {};
	this.json = {};
	this.obj = {};	
})();

//From http://stackoverflow.com/questions/20484738/submit-form-on-enter-key-with-javascript
 document.onkeydown=function(evt)
 {
        var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
        if(keyCode == 13)
        {
         	var barcode = document.getElementById("barcodeSearch").value;
			searchBarcode(barcode);
        }
}

function product(pBarcode, pName, pCategory, pPrice) {
    this.barcode = pBarcode;
    this.name = pName;
    this.category = pCategory;
    this.price = pPrice;
}

var sale = new (function()
{
	this.subTotal = 0;
	this.products = [];	
	
	this.addProduct = function(pProduct)
	{
		this.subTotal += pProduct.price;	
		this.products.push(pProduct);
	}
})();

function loadJSON()
{	
	jsonLoader.http = new XMLHttpRequest();
	
	
	jsonLoader.http.onreadystatechange = function()
	{
		if(jsonLoader.http.readyState === 4 && jsonLoader.http.status === 200)			
		{
			//Create variable to stores response text. 
			jsonLoader.json = jsonLoader.http.responseText;
			//Create object to store parsed response text. 
			jsonLoader.obj = JSON.parse(jsonLoader.json);
			//Create category list.
			buildCategoryList();
		}
	};	

	jsonLoader.http.open("GET", "products.json", true);
	jsonLoader.http.send();
}

function buildCart(pSale)
{
	var productsDiv = document.getElementById("productsDiv");
	productsDiv.innerHTML = "";

	// while (productsDiv.hasChildNodes())
	// {  
		// productsDiv.removeChild(productsDiv.firstChild);
	// }	

	pSale.subTotal = 0;
	
	for(i = 0; i < pSale.products.length; i++)
	{		
		var cartString = pSale.products[i].name + " " + pSale.products[i].price + " " + pSale.products[i].category + "<br>";			
		var newDiv = document.createElement("div");
		newDiv.id = i;
		newDiv.innerHTML = cartString;				
		productsDiv.appendChild(newDiv);
		newDiv.onclick = function() {removeOnClick(this.id);};
		
		if(i % 2 == 0)
		{
			newDiv.style.backgroundColor = "aquamarine";
		}
		else
		{
			newDiv.style.backgroundColor= "aqua";
		}
		
		sale.subTotal += pSale.products[i].price;
		cartString += sale.subTotal;	
	}
	
	var subTotalDiv = document.createElement("div");
	productsDiv.appendChild(subTotalDiv);	
	subTotalDiv.innerHTML = "Subtotal: $" + sale.subTotal.toFixed(2);	
	subTotalDiv.style.backgroundColor = "lightgreen";
}

function completeSale()
{
	var saleString = "Receipt\n\nItems: \n";
	var saleTax = sale.subTotal * 0.15;
	var saleTotal = sale.subTotal + saleTax;
	for(var i = 0; i < sale.products.length; i++)
	{
		saleString += sale.products[i].name + " " + sale.products[i].price + "\n";		
	}
	saleString += "\nSubtotal: " + sale.subTotal.toFixed(2) + "\n";
	saleString += "Tax: " + saleTax.toFixed(2) + "\n";
	saleString += "Final Total: " + saleTotal.toFixed(2);
	
	window.alert(saleString);
}

function removeOnClick(index)
{
	sale.products.splice(index, 1);
	buildCart(sale);
}

function searchBarcode(barcode)
{	
	//If a submitted barcode is contained within the JSON file.
	for(var i = 0; i < jsonLoader.obj.products.length; i++)
	{
		if (barcode == jsonLoader.obj.products[i].barcode)
		{					
			//If a barcode match is found, create a product object and fill it with related values. 
			var thisProduct = new product(jsonLoader.obj.products[i].barcode, jsonLoader.obj.products[i].name, jsonLoader.obj.products[i].category, jsonLoader.obj.products[i].price);
					
			//Using function / closures
			sale.addProduct(thisProduct);					
			
			//productString += thisProduct.name;								
			buildCart(sale);
			
		}
	}
	
	document.getElementById("barcodeSearch").value = "";
}

function searchCategory(pCategory)
{
	categoryString = "";
	categoryDisplay = document.getElementById("categoryDisplay");
	categoryDisplay.innerHTML = "";
	
	for(var i = 0; i < jsonLoader.obj.products.length; i++)
	{
		if(jsonLoader.obj.products[i].category == pCategory)
		{
			categoryString = jsonLoader.obj.products[i].barcode + " " + jsonLoader.obj.products[i].name + " " + jsonLoader.obj.products[i].price.toFixed(2);
			var categoryDiv = document.createElement("div");
			categoryDiv.innerHTML = categoryString;
			categoryDiv.id = jsonLoader.obj.products[i].barcode;
			categoryDiv.onclick = function() {sendCategory(this.id)};
			categoryDisplay.appendChild(categoryDiv);			
		}
	}
}

function sendCategory(pId)
{
	document.getElementById("barcodeSearch").value = pId;
}

function buildCategoryList()
{
	//Fill category selector list with categories from product list. Works, but still shows up as obj undefined.
	var categorySelector = document.getElementById("categorySelect");
	var categoryOption = document.createElement("option");
		categorySelector.appendChild(categoryOption);
		categoryOption.innerHTML = "";

	//Populate category list with categories from JSON file. Remove duplicates.
	var addCategory = true;
	for(var i = 0; i < jsonLoader.obj.products.length; i++)
	{		
		for(var j = 0; j < categorySelector.children.length; j++)
		{
			if(jsonLoader.obj.products[i].category == categorySelector.children[j].innerHTML)
			{
				addCategory = false;
			}		
		}

		if(addCategory == true)
		{
			var categoryOption = document.createElement("option");
			categorySelector.appendChild(categoryOption);
			categoryOption.innerHTML = jsonLoader.obj.products[i].category;
		}
		
		else
		{
			addCategory = false;
		}					
	}
}