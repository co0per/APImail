package tp.Services;

import tp.DAO.MensajeDAO;
import tp.Model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MensajeService {

    @Autowired
    MensajeDAO mensajeDao;

    @Autowired
    public MensajeService(MensajeDAO dao) {
        this.mensajeDao = dao;
    }

    public void enviarMensajeService(Mensaje mail){ mensajeDao.enviarMail(mail); }

    public ArrayList<Mensaje> getMensajesService(String mail) { return mensajeDao.traerEnviados(mail); }
}