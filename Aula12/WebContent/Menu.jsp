<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Inicio</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="ServletController?command=ListaPaisReiniciar">Listar Paises</a>
                    </li>
                    <li><a href="CriarPais.jsp">Criar</a>
                    </li>
                    <li><a href="ListaDePais.jsp">Buscar</a>
                    </li>
                    <li><a href="#">Países</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>