using Microsoft.OData.Edm;

namespace WebAPI.Data
{
    public class HoaDonModel
    {
        public int ID_HoaDon { get; set; }
        public DateTime NgayLap { get; set; }
        public int TongTien { get; set; }
        public int ID_Account { get; set; }
    }
}
