package com.example.records.service;

import com.example.records.dto.RecordRequestDto;
import com.example.records.dto.RecordResponseDto;
import com.example.records.entity.Record;
import com.example.records.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {
    
    private final RecordRepository recordRepository;
    
    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
    
    public RecordResponseDto createRecord(RecordRequestDto requestDto) {
        Record record = new Record(requestDto.getName(), requestDto.getMessage(), requestDto.getNote());
        Record savedRecord = recordRepository.save(record);
        return convertToDto(savedRecord);
    }
    
    @Transactional(readOnly = true)
    public List<RecordResponseDto> getAllRecords() {
        List<Record> records = recordRepository.findAllOrderByCreatedAtDesc();
        return records.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<RecordResponseDto> getRecordById(Long id) {
        return recordRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public Optional<RecordResponseDto> updateRecord(Long id, RecordRequestDto requestDto) {
        Optional<Record> existingRecord = recordRepository.findById(id);
        
        if (existingRecord.isPresent()) {
            Record record = existingRecord.get();
            
            // Update only if fields are provided and not empty (for name and message)
            if (requestDto.getName() != null && !requestDto.getName().trim().isEmpty()) {
                record.setName(requestDto.getName());
            }
            
            if (requestDto.getMessage() != null && !requestDto.getMessage().trim().isEmpty()) {
                record.setMessage(requestDto.getMessage());
            }
            
            // note can be null or empty string
            if (hasNoteField(requestDto)) {
                record.setNote(requestDto.getNote());
            }
            
            Record updatedRecord = recordRepository.save(record);
            return Optional.of(convertToDto(updatedRecord));
        }
        
        return Optional.empty();
    }
    
    public boolean deleteRecord(Long id) {
        if (recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private RecordResponseDto convertToDto(Record record) {
        return new RecordResponseDto(
                record.getId(),
                record.getName(),
                record.getMessage(),
                record.getNote(),
                record.getCreatedAt(),
                record.getUpdatedAt()
        );
    }
    
    private boolean hasNoteField(RecordRequestDto requestDto) {
        // Check if note field was passed in the request
        // In a real project, a more elegant approach could be used,
        // for example, through @JsonProperty or custom validation
        return true; // For now, always update note if it exists in DTO
    }
}