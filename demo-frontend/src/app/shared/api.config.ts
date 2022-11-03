import { environment } from '../../environments/environment';

export const API_CONFIG = {
  authUrl: environment.authBaseUrl,
  login: environment.authBaseUrl + '/oauth/token',
  logout: environment.authBaseUrl + '/logout',
  getAllProfiles: environment.adminBaseUrl + '/getAllProfiles',
  getAllSections: environment.adminBaseUrl + '/getAllSections',

  // Contacts API
  getContacts: environment.contactsBaseUrl + '/getContacts',
  getContact: environment.contactsBaseUrl + '/getContact',
  createContact: environment.contactsBaseUrl + '/createContact',
  editContact: environment.contactsBaseUrl + '/editContact',
  deleteContact: environment.contactsBaseUrl + '/deleteContact',

  // Users API
  createUser: environment.usersBaseUrl + '/createUser',

  // Product API
  createProduct: environment.productsBaseUrl + '/createProduct',
  uploadProductImg: environment.productsBaseUrl + '/upload',
  deleteProduct: environment.productsBaseUrl + '/deleteProduct',
  getProductsForCategory: environment.productsBaseUrl + '/getAllProductsByCategory',

  // Category API
  getCategories: environment.categoriesBaseUrl + '/getCategories',
  getCategory: environment.categoriesBaseUrl + '/getCategory',
};
