package org.project.java.manga_backend.service;

import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Status;
import org.project.java.manga_backend.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Optional<Status> findById(Integer id) {
        return statusRepository.findById(id);
    }

    public Status getById(Integer id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isEmpty()) {
            return null;
        } else {
            return status.get();
        }
    }

    public Status store(Status status) {
        return statusRepository.save(status);
    }

    public Status update(Status status) {
        return statusRepository.save(status);
    }

    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }

    public void delete(Status status) {
        statusRepository.delete(status);
    }

}
