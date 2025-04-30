package com.notes.notes_app.service;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.model.Notes;
import com.notes.notes_app.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    CustomerService customerService;

    public List<Notes> getNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerService.getCustomer(username);
        return customer.getNotes();
    }

    public Notes addNote(Notes note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerService.getCustomer(username);
        Notes saved = notesRepository.save(note);
        customer.getNotes().add(note);
        customerService.addCustomer(customer);
        return saved;
    }

    public Notes updateNote(Notes note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerService.getCustomer(username);
        for (int i=0;i<customer.getNotes().size();i++) {
            if (note.getId()==customer.getNotes().get(i).getId()){
                Notes saved = notesRepository.save(note);
                Notes newNote = customer.getNotes().set(i, note);
                customerService.addCustomer(customer);
                return newNote;
            }
        }
        return new Notes();
    }

    public void deleteNote(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerService.getCustomer(username);
        for (int i=0;i<customer.getNotes().size();i++) {
            if (id==customer.getNotes().get(i).getId()) {
                customer.getNotes().remove(i);
                notesRepository.deleteById(id);
            }
        }
    }
}
