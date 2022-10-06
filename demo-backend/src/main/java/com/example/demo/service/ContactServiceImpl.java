package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;

@Service
public class ContactServiceImpl extends AbstractDemoService implements IContactService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private ContactRepository contactRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContactDTO getContact(Integer id) {
		Contact contact = contactRepository.findById(id).orElse(null);	
		return ContactMapper.INSTANCE.contactToContactDto(contact);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<ContactDTO>> getContacts(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Contact> contacts = SpecificationBuilder.selectDistinctFrom(contactRepository).where(pageFilter)
				.findAll(pageFilter); 
		DataSourceRESTResponse<List<ContactDTO>> datares = new DataSourceRESTResponse<>();
		datares.setTotalElements((int) contacts.getTotalElements());
		List<ContactDTO> lContactDTO = ContactMapper.INSTANCE.contactToContactDtoList(contacts.getContent());
		datares.setData(lContactDTO);
		return datares;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ContactDTO createContact(ContactDTO createContactRequest) {
		Contact contact = ContactMapper.INSTANCE.contactDTOtoContact(createContactRequest);
		Contact newContact = contactRepository.save(contact);
		return ContactMapper.INSTANCE.contactToContactDto(newContact);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Integer deleteContact(Integer id) {
		contactRepository.deleteById(id);
		return id;

	}

	@Override
	public List<ContactDTO> findAll() {
		List<Contact> lContacts = contactRepository.findAll();
		return ContactMapper.INSTANCE.contactToContactDtoList(lContacts);
	}

	@Override
	public Integer editContact(ContactDTO editContactRequest) {
		Contact contactFromDTO = ContactMapper.INSTANCE.contactDTOtoContact(editContactRequest); 
		Contact editContact = contactRepository.save(fromEditContactRequest(contactFromDTO));
		return editContact.getId();
	}

}
