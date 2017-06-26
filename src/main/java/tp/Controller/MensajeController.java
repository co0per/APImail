package tp.Controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import tp.Request.MensajeRequest;
import tp.Wrapper.MensajeWrapper;
import tp.Services.MensajeService;
import tp.Services.UsuarioService;
import tp.Model.Mensaje;

@RestController
public class MensajeController
{
    @Autowired
    MensajeService mensajeService;

    @RequestMapping(value = "/api/enviar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity enviarMensaje (@RequestBody Mensaje mensaje){
        try{
            mensajeService.enviarMensajeService(mensaje);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @RequestMapping(value = "api/mensaje", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ArrayList<MensajeWrapper>> getMensajesEnviados(@RequestParam(value = "remitente") String remi){
        try{
            ArrayList<Mensaje> lista_inbox = mensajeService.getMensajesService(remi);
            if (lista_inbox.isEmpty()){
                return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertList(listMensajes), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}