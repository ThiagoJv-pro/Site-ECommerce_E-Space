// Carregar as variáveis de ambiente do arquivo .env
require('dotenv').config();

// Definição das variáveis de ambiente
var returnObj = process.env.RETURN_OBJ;
var returnAllObj = process.env.RETURN_ALL_OBJ;
var returnProductType = process.env.RETURN_PRODUCT_TYPE;

// Importação dos módulos necessários
var express = require('express');
var router = express.Router();
var axios = require('axios');
var bodyParser = require('body-parser');

// Definição da classe ReturnData
class ReturnData {
    // Função assíncrona para retornar os dados de um objeto com base em um ID
    async returnDataObj(id) {
        try {
            var response = await axios.get(returnObj + id);
            var apiData = response.data;
            return apiData;
        } catch (error) {
            console.error(error);
            return null;
        }
    }

    // Função assíncrona para retornar todos os dados de objetos
    async allReturnDataObj() {
        try {
            var response = await axios.get(returnAllObj);
            var apiData = response.data;
            return apiData;
        } catch (error) {
            console.error(error);
            return null;
        }
    }

    // Função assíncrona para retornar dados filtrados com base no tipo de produto
    async returnFilter(productType) {
        try {
            var response = await axios.get(returnProductType + productType);
            var apiData = response.data;
            return apiData;
        } catch (error) {
            console.error(error);
            return null;
        }
    }
}

// Exportação da classe ReturnData
module.exports = ReturnData;
