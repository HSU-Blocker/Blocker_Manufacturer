// src/api/musicService.js
import api from './api';
import { API_CONFIG } from '../config';

export const MusicService = {
  startSession: async (params) => {
    return await api.post(API_CONFIG.ENDPOINTS.START, params);
  },

  getInfo: async () => {
    return await api.get(API_CONFIG.ENDPOINTS.INFO);
  },

  getPreview: async (id) => {
    return await api.get(`${API_CONFIG.ENDPOINTS.PREVIEW}/${id}`);
  }
};