from flask import Flask, jsonify, make_response
from Service import productService as ps

app = Flask(__name__)

@app.route("/datareturn/<typeFilter>", methods=['GET'])
def dataReturn(typeFilter):
    # Chama a função 'dataReturnType' do módulo 'productService' do pacote 'Service'
    return ps.dataReturnType(typeFilter)
