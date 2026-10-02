package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.TheatreDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.TheatreDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.TheatreEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreServiceIMPL implements TheatreService {

    @Autowired
    private TheatreDAO theatreDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TheatreDTO saveTheatre(TheatreDTO theatreDTO) {
        TheatreEntity theatreEntity = modelMapper.map(theatreDTO, TheatreEntity.class);
        TheatreEntity savedTheatre = theatreDAO.save(theatreEntity);
        return modelMapper.map(savedTheatre, TheatreDTO.class);
    }

    @Override
    public TheatreDTO updateTheatre(Long id, TheatreDTO theatreDTO) {
        TheatreEntity existingTheatre = theatreDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));

        existingTheatre.setName(theatreDTO.getName());
        existingTheatre.setLocation(theatreDTO.getLocation());
        existingTheatre.setCapacity(theatreDTO.getCapacity());
        existingTheatre.setStatus(theatreDTO.getStatus());
        existingTheatre.setSeatMapUrl(theatreDTO.getSeatMapUrl());

        TheatreEntity updatedTheatre = theatreDAO.save(existingTheatre);
        return modelMapper.map(updatedTheatre, TheatreDTO.class);
    }

    @Override
    public void deleteTheatre(Long id) {
        if (!theatreDAO.existsById(id)) {
            throw new RuntimeException("Theatre not found with id: " + id);
        }
        theatreDAO.deleteById(id);
    }

    @Override
    public TheatreDTO getTheatreById(Long id) {
        TheatreEntity theatreEntity = theatreDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
        return modelMapper.map(theatreEntity, TheatreDTO.class);
    }

    @Override
    public List<TheatreDTO> getAllTheatres() {
        return theatreDAO.findAll().stream()
                .map(theatre -> modelMapper.map(theatre, TheatreDTO.class))
                .collect(Collectors.toList());
    }
}