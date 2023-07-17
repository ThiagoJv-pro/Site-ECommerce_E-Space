var express = require('express'); // Importa o módulo express
var router = express.Router(); // Cria um objeto de roteador usando o express.Router()
var axios = require('axios'); // Importa o módulo axios
var bodyParser = require('body-parser'); // Importa o módulo body-parser
var ReturnData = require("./Api/ReturnData"); // Importa a classe ReturnData do arquivo ReturnData.js
var DataModel = require("./Models/DataModel"); // Importa a classe DataModel do arquivo DataModel.js
var returnData = new ReturnData(); // Cria uma instância da classe ReturnData para utilizá-la
var urlroot = process.env.URLROOT;
var urlrootProducts = process.env.URLROOT_PRODUCTS;
var dataModel = new DataModel(); // Cria uma instância da classe DataModel para utilizá-la
require('dotenv').config()

/* GET home page. */
router.get('/', async function(req, res, next) {
  var filterOne = await returnData.returnFilter("Capacete"); // Chama o método returnFilter("Capacete") da instância returnData
  var filterTwo = await returnData.returnFilter("Roupa"); // Chama o método returnFilter("Roupa") da instância returnData

  try {
    res.render('home', {
      title: 'e-commerce',
      filterOne: filterOne,
      filterTwo: filterTwo,
      locallink: urlroot,
      locallinkProducts: urlrootProducts
    });
  } catch (error) {
    res.render('home', {
      filterOne: "Null",
      filterTwo: "Null",
      locallink: urlroot,
      locallinkProducts: urlrootProducts
    });
  }
});

router.get('/item/:_id', async function(req, res, next) {
  var id = encodeURIComponent(req.params._id); // Codifica o valor do parâmetro _id antes de concatená-lo na URL
  console.log(id);
  try {
    var jsonApi = await returnData.returnDataObj(id); // Chama o método returnDataObj(id) da instância returnData
    res.render("productPage", {
      title: "ProductPage",
      doc: dataModel.returnObjImg(jsonApi)[0], // Chama o método returnObjImg(jsonApi) da instância dataModel e acessa o primeiro elemento retornado
      Subimg1: dataModel.returnObjImg(jsonApi)[1], // Chama o método returnObjImg(jsonApi) da instância dataModel e acessa o segundo elemento retornado
      Subimg2: dataModel.returnObjImg(jsonApi)[2], // Chama o método returnObjImg(jsonApi) da instância dataModel e acessa o terceiro elemento retornado
      Subimg3: dataModel.returnObjImg(jsonApi)[3], // Chama o método returnObjImg(jsonApi) da instância dataModel e acessa o quarto elemento retornado
      price: dataModel.returnPrice(jsonApi), // Chama o método returnPrice(jsonApi) da instância dataModel
      name: dataModel.returnName(jsonApi), // Chama o método returnName(jsonApi) da instância dataModel
      description: dataModel.returnDescription(jsonApi).description, // Chama o método returnDescription(jsonApi) da instância dataModel e acessa a propriedade description
      type: dataModel.returnDescription(jsonApi).type, // Chama o método returnDescription(jsonApi) da instância dataModel e acessa a propriedade type
      color: dataModel.returnDescription(jsonApi).color, // Chama o método returnDescription(jsonApi) da instância dataModel e acessa a propriedade color
      material: dataModel.returnDescription(jsonApi).material, // Chama o método returnDescription(jsonApi) da instância dataModel e acessa a propriedade material
      brand: dataModel.returnDescription(jsonApi).brand, // Chama o método returnDescription(jsonApi) da instância dataModel e acessa a propriedade brand
      locallink: urlroot,
      locallinkProducts: urlrootProducts
    });
  } catch (error) {
    res.render("productPage", {
      title: "ProductPage",
      doc: "Null",
      Subimg1: "Null",
      Subimg2: "Null",
      Subimg3: "Null",
      price: "Null",
      name: "Null",
      description: "Null",
      type: "Null",
      color: "Null",
      material: "Null",
      brand: "Null",
      locallink: urlroot,
      locallinkProducts: urlrootProducts
    });
  }
});

router.get("/products", async function(req, res) {
  var filterType = req.query.type;
  var allReturnData;
 
  
    try {
      if(typeof filterType == "undefined" ||  filterType == ""){
        allReturnData = await returnData.allReturnDataObj(); // Chama o método allReturnDataObj() da instância returnData
        res.render('productsPage', {
          title: 'e-commerce',
          doc: allReturnData,
          locallink: urlroot,
          locallinkProducts: urlrootProducts
        });
    }else{
      allReturnData = await returnData.returnFilter(filterType); 
      console.log(allReturnData);
      res.render('productsPage', {
        title: 'e-commerce',
        doc: allReturnData,
        locallink: urlroot,
        locallinkProducts: urlrootProducts
      });
    }
    } catch (error) {
      res.render('productsPage', {
        title: 'e-commerce',
        doc: "NULL",
        locallink: urlroot,
        locallinkProducts: urlrootProducts
      });
    }    
 });

module.exports = router; // Exporta o objeto de roteador definido para ser usado em outros arquivos
