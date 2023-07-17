// Importação dos módulos necessários
var express = require('express');
var router = express.Router();
var axios = require('axios');
var bodyParser = require('body-parser');

// Definição da classe DataModel
class DataModel {
    // Função para retornar um array de links de imagem
    returnObjImg(dataImg) {
        var linkImage = [dataImg.img.img1, dataImg.img.img2, dataImg.img.img3, dataImg.img.img4];
        return linkImage;
    }

    // Função para retornar o preço do produto
    returnPrice(dataPrice) {
        var priceProduct = dataPrice.value;
        return priceProduct;
    }

    // Função para retornar o nome do produto
    returnName(dataName) {
        var productName = dataName.name;
        return productName;
    }

    // Função para retornar a descrição do produto
    returnDescription(dataDescription) {
        var descriptionProduct = {
            description: dataDescription.description.productDescription,
            type: dataDescription.description.type,
            color: dataDescription.description.color,
            material: dataDescription.description.material,
            brand: dataDescription.description.brand
        };

        return descriptionProduct;
    }
}

// Exportação da classe DataModel
module.exports = DataModel;
