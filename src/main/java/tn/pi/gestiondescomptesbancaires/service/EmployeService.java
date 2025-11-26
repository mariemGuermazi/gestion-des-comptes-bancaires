
/**package tn.pi.gestiondescomptesbancaires.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.model.Employe;
r
import tn.pi.gestiondescomptesbancaires.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeService {
    private final EmployeRepository employeRepository;
    public List<Employe> getAllEmployes() { return employeRepository.findAll(); }
    public Employe saveEmploye(Employe e) { return employeRepository.save(e); }
    public Employe getEmploye(Long id) { return employeRepository.findById(id).orElse(null); }
    public void deleteEmploye(Long id) { employeRepository.deleteById(id); }

}**/

