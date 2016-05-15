package com.example.pabji.applectorrss.utils;

public enum UrlType {
    Andalucia, Madrid, Cataluya, ComunidadValenciana,PaisVasco, Galicia ;

    public static UrlType UrlTypeFromIndex(int index) {
        switch (index) {
            case 1:
                return UrlType.Madrid;
            case 2:
                return UrlType.Cataluya;
            case 3:
                return UrlType.ComunidadValenciana;
            case 4:
                return UrlType.PaisVasco;
            case 5:
                return UrlType.Galicia;

        }
        return UrlType.Andalucia;
    }

}
