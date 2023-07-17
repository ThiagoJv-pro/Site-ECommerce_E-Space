import requests
from flask import Flask, jsonify, make_response

def dataReturnType(typeFilter):
    # URL da API para buscar os dados
    url = ''

    # Faz uma requisição GET para a API
    req = requests.get(url)

    # Dicionários para armazenar os resultados filtrados e a resposta final
    filterDict = {}
    response = {}

    # Variáveis para armazenar dados específicos de cada resultado
    ind = 0
    img = ""
    end = ""
    price = 0.0

    # Armazena a resposta da API em uma variável
    response_list = req.json()

    # Converte a lista em um dicionário
    response_dict = {str(index + 1): element for index, element in enumerate(response_list)}

    # Itera sobre cada chave e valor no dicionário de resposta
    for l, filter_ in response_dict.items():
        filterDict = filter_  # Armazena os valores obtidos em uma variável
        descriptionImg = filterDict.get('img')
        descriptionProduct = filterDict.get('description')  # Recupera os valores da chave 'description'
        descriptionProductType = descriptionProduct.get('type')  # Recebe o valor da chave 'type'

        # Verifica se o tipo do produto corresponde ao filtro especificado
        if descriptionProductType == typeFilter:
            # Armazena os dados específicos do resultado filtrado nas variáveis correspondentes
            ind = filterDict.get('id')
            imgCard = descriptionImg.get('img1')
            end = descriptionProduct.get('productDescription')
            price = filterDict.get('value')
            name = filterDict.get('name')

            # Adiciona os dados do resultado filtrado ao dicionário de resposta
            response[l] = dict(id=ind, name=name, img=imgCard, description=end, value=price)

    # Converte os valores do dicionário de resposta em uma lista
    values_list = [u for u in response.values()]

    # Retorna a resposta como uma resposta HTTP com formato JSON
    return make_response(jsonify(values_list))
