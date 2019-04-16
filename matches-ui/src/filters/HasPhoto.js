
class HasPhotoFilter {
    
    getQueryString(filters) {
        return 'hasPhoto' in filters && filters['hasPhoto'] ? '&has_photo=true' : ''
    }

}

export default (HasPhotoFilter);