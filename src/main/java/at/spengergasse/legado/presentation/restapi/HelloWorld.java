package at.spengergasse.legado.presentation.restapi;


public record HelloWorld(String message)
{
    public boolean isValid()
    {
        return true;
    }
}
