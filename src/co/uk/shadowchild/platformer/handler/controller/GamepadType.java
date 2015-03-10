package co.uk.shadowchild.platformer.handler.controller;


public enum GamepadType{

    XBOX360("x360"),
    XBOXONE("xbone"),
    DIRECTINPUT("directinput"),
    XINPUT("xinput");

    GamepadType(String sectionName){
    
        this.sectionName = sectionName;
    }
    public String sectionName;
}
