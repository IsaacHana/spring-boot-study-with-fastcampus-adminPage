import { Link, useLocation, useNavigate } from "react-router-dom";

interface TableProps<T> {
  data: T[] | null;
  keys: string[];
  tableHeads: string[];
}

type TableComponentI<T = any> = React.FC<TableProps<T>>;

const Table: TableComponentI = ({ data, keys, tableHeads }) => {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  console.log(pathname);

  return (
    <>
      {data ? (
        <>
          <div className="relative overflow-x-auto shadow-md rounded-lg">
            <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
              <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400 rounded-lg">
                <tr>
                  {tableHeads.map((value) => (
                    <th scope="col" className="px-6 py-3">
                      {value}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {data.map((d) => (
                  <tr
                    key={d.id}
                    onClick={() => {
                      navigate(`${pathname}/${d.id}`);
                    }}
                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:opacity-80 transition-all hover:text-stone-200"
                  >
                    {keys.map((key) => (
                      <td className="text-middle px-6 py-4">{d[key]}</td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </>
      ) : (
        <></>
      )}
    </>
  );
};

export default Table;
