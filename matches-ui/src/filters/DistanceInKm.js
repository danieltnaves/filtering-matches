
class DistanceInKm {
    
    getQueryString(filters) {
        return 'distanceInKm' in filters && filters['distanceInKm'] > 30 ? '&distance_in_km=' + filters['distanceInKm'] + '&longitude=-0.118092&latitude=51.509865' : ''
    }

}

export default (DistanceInKm);