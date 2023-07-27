package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoDao departamentoDao;

    @Autowired
    public DepartamentoServiceImpl(DepartamentoDao departamentoDao) {
        this.departamentoDao = departamentoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> listadoDepartamento() {
        return departamentoDao.findByEstadoDepartamentoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Departamento> listadoDepartamentoPaginado(Pageable pageable) {
        return departamentoDao.findAllByEstadoDepartamentoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarDepartamento(Departamento departamento) {
        departamento.setEstadoDepartamento(true);
        departamentoDao.save(departamento);
    }

    @Override
    @Transactional
    public void eliminarDepartamento(Departamento departamento) {
        departamentoDao.delete(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento encontrarDepartamento(Departamento departamento) {
        return departamentoDao.findById(departamento.getIdDepartamento()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDepartamento(Departamento departamento) {
        Departamento departamentoExistente = departamentoDao.findById(departamento.getIdDepartamento()).orElse(null);
        if (departamentoExistente != null) {
            departamentoExistente.setEstadoDepartamento(false);
            departamentoDao.save(departamentoExistente);
        }
    }

}
